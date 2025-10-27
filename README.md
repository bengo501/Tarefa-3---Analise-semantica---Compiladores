# Tarefa 3 - Construção de Compiladores
## Integrantes: Bernarno Klein, João Pedro Aiolfi, Lucas Brenner e Lucas Lantmann

### Como executar o projeto:
cd Tarefa

javac *.java

java Parser corretoStruct.txt

java Parser erroStruct.txt

---
### Compilação do programa

java -jar jflex.jar exemploSem.flex

.\yacc.exe -J exemploSem.y

javac *.java

java Parser corretoStruct.txt

java Parser erroStruct.txt