# Tomcat 10 base image
FROM tomcat:10.1-jdk17-temurin

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/ROOT/*

# Copy application files into ROOT directory
COPY ApnaBank.html /usr/local/tomcat/webapps/ROOT/index.html
COPY atm.css /usr/local/tomcat/webapps/ROOT/
COPY result.jsp /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080

CMD ["catalina.sh", "run"]
