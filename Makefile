JC = javac

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java

CLASSES = network/*.java \
		gui/*.java

all:
	$(JC) $(CLASSES)

run: all
	java network.Client

serv: all
	java network.Server
	
classes: $(CLASSES:.java=.class)

clean:
		$(RM) network/*.class
		$(RM) gui/*.class