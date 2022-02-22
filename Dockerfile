FROM mcr.microsoft.com/java/tomcat:8-zulu-alpine-tomcat-9
COPY target/pldemo.war /usr/local/tomcat/webapps/ROOT.war

