# 1. Base Image with JDK and Tomcat 10
FROM tomcat:10.1-jdk17-temurin

# 2. Clear default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/ROOT/*

# 3. Create required directory structure for Servlets
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# 4. Copy Java file and compile it using Tomcat's servlet library
COPY CheckAmount.java /tmp/
RUN javac -cp /usr/local/tomcat/lib/servlet-api.jar -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes /tmp/CheckAmount.java

# 5. Copy frontend assets & JSP to ROOT webapp
COPY ApnaBank.html /usr/local/tomcat/webapps/ROOT/index.html
COPY atm.css /usr/local/tomcat/webapps/ROOT/
COPY result.jsp /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080

CMD ["catalina.sh", "run"]
