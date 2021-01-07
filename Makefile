JC = javac

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java

CLASSES = src/network/*.java \
		src/gui/*.java

all:
	$(JC) $(CLASSES)

run: all
	java src.network.Client

serv: all
	java src.network.Server
	
classes: $(CLASSES:.java=.class)

clean:
		$(RM) src/network/*.class
		$(RM) src/gui/*.class