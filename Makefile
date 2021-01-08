JC = javac

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java

CLASSES = network/*.java \
		gui/*.java \
		game/*.java

all:
	$(JC) -cp "/Users/violettagoldman/Downloads/commons-lang3-3.11/commons-lang3-3.11.jar:/Users/violettagoldman/Downloads/commons-lang3-3.11/commons-lang3-3.11-tests.jar:/Users/violettagoldman/Downloads/commons-lang3-3.11/commons-lang3-3.11-sources.jar:/Users/violettagoldman/Downloads/commons-lang3-3.11/commons-lang3-3.11-javadoc.jar" $(CLASSES)

run: all
	java network.Client

serv: all
	java network.Server
	
classes: $(CLASSES:.java=.class)

clean:
		$(RM) network/*.class
		$(RM) gui/*.class
		$(RM) game/*.class