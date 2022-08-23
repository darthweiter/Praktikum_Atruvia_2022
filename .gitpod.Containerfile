FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 17.0.4-ms && \
    sdk default java 17.0.4-ms"
    
RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install maven 3.8.6 && \
    sdk default maven 3.8.6"
