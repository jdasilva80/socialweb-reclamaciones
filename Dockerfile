FROM openjdk:8
VOLUME /tmp
ADD ./target/socialweb-reclamaciones-0.0.1-SNAPSHOT.jar socialweb-reclamaciones.jar
ENTRYPOINT ["java","-jar","/socialweb-reclamaciones.jar"]