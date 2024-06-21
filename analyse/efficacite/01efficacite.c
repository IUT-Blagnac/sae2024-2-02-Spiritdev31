#include <malloc.h>
#include <assert.h>

/*
 * Pour être efficace, ce programme ne fait pas de strlen pour calculer
 * la longueur du texte à coder et évite ainsi de parcourir deux fois
 * la chaîne.
 *
 * En contrepartie il faut quelques fonction pour avoir une zone temporaire
 * extensible où stocker le résultat. À la fin on redimensionne à la taille
 * exacte de la chaîne (pas de gaspillage mémoire).
 *
 * Ces fonctions sont inline static, c'est à dire qu'on copie-colle leur
 * code au lieu de les appeler (plus efficace).
 *
 * La zone temporaire est petite au départ (l'équivalent d'une ligne de
 * texte) puis augmente par gros morceaux si la texte à coder est énorme.
 */

/* Structure et fonctions pour la zone temporaire. */

typedef struct {
  char *buf;
  size_t size;
  size_t i;
} charbuf_t;

static inline int init(charbuf_t *cb, int size) {
  cb->size = size;
  cb->buf = malloc(cb->size + 1); /* Provisionning the '\0' */
  if (cb->buf == NULL) return 0;
  cb->i = 0;
  return 1;
}

static inline int extend(charbuf_t *cb, int extra_size) {
  cb->size += extra_size;
  cb->buf = realloc(cb->buf, cb->size + 1);
  if (cb->buf == NULL) return 0;
  return 1;
}

static inline void write(charbuf_t *cb, char c) {
  cb->buf[cb->i++] = c;
}

static inline size_t spare(charbuf_t *cb) {
  return cb->size - cb->i;
}

static inline char *finish(charbuf_t *cb) {
  cb->buf[cb->i] = '\0';
  cb->buf = realloc(cb->buf, cb->i + 1);
  return cb->buf;
}

static inline char *cancel(charbuf_t *cb) {
  free(cb->buf);
  return NULL;
}


/* Codage et décodage RLE9 */

#define BLOCK_INI 128
#define BLOCK_EXT 1024

/**
 * Encodage RLE9.
 */
char *rle(char *txt) {
  charbuf_t res;
  if (!init(&res, BLOCK_INI)) return NULL;

  if (txt[0] == '\0') return finish(&res); /* Empty string case. */

  size_t i_txt = 0; /* index for the input text */
  char current = txt[i_txt++];  /* first char is current char */
  int count = 1; /* one occurence so far */
  char c;
  while ((c = txt[i_txt++])) {
    if (c == current) { /* Same character */
      count++;
      if (count == 10) {
	write(&res, '9');
	write(&res, current);
	if (!spare(&res)) {
	  if (!extend(&res, BLOCK_EXT)) return cancel(&res);
	}
	count = 1;
      }
    } else { /* Different character */
      write(&res, '0' + count);
      write(&res, current);
      if (!spare(&res)) {
	if (!extend(&res, BLOCK_EXT)) return cancel(&res);
      }
      current = c;
      count = 1;
    }
  }
  write(&res, '0' + count);
  write(&res, current);

  return finish(&res);
}

/**
 * Décodage RLE9
 * @return La chaîne décodée ou NULL si l'entrée est invalide.
 */
char *unrle(char *coded) {
  charbuf_t res;
  if (!init(&res, BLOCK_INI)) return NULL;

  size_t i = 0;
  char cnum;
  while((cnum = coded[i++])) {
      int num = cnum - '0';
      if (num < 1 || num > 9) return cancel(&res);  /* not a number */
      char cchar = coded[i++];
      if (cchar == '\0') return cancel(&res);  /* no char to replicate */

      /* need to write cnum * cchar */
      if (spare(&res) < cnum) {
	if (!extend(&res, BLOCK_EXT)) return cancel(&res);
      }
      while(num--) {
	write(&res, cchar);
      }
  }
  return finish(&res);
}


/* Programme illustratif. */

int main(void) {
  char *txt = "abbcccwwwwwwwwwwwwwwwwwwwwabb";
  printf("Texte avant : \"%s\"\n", txt);
  char *compr = rle(txt);
  printf("Texte apres : \"%s\"\n", compr);
  char *decompr = unrle(compr);
  printf("Texte decod : \"%s\"\n", decompr);
  free(compr);
  free(decompr);
}
