LATEXMK=latexmk
DIPLOMA_SRC=dissertation.tex

all: dissertation

dissertation:
	$(LATEXMK) -pdf $(DIPLOMA_SRC)

.PHONY: clean
clean:
	$(LATEXMK) -c
	(cd tex; $(LATEXMK) -c)
	rm -f *.bbl *.synctex.gz