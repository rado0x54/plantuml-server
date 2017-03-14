FROM jetty

# PlantUML requires Graphviz
RUN apt-get -y update && apt-get install -y graphviz

ADD build/libs/plantuml.war /var/lib/jetty/webapps/root.war

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/ || exit 1