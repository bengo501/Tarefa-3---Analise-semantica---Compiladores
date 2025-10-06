# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/


#
# atenção, copie para a pasta de tabalho
# os arquivos JFlex.jar e yacc.linux
#

JFLEX  = java -jar JFlex.jar 
BYACCJ = ./yacc.linux -tv -J
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	rm -f *~ *.class Yylex.java Parser.java y.output

Parser.class: TS_entry.java TabSimb.java Yylex.java Parser.java
	$(JAVAC) Parser.java

Yylex.java: exemploSem.flex
	$(JFLEX) exemploSem.flex

Parser.java: exemploSem.y
	$(BYACCJ) exemploSem.y
