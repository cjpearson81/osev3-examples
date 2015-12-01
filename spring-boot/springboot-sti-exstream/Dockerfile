#springboot-sti-exstream
FROM springboot-sti

RUN mkdir -p /opt/exstream \
    mkdir -p /opt/exstream/8.0/ \
    mkdir -p /opt/exstream/pubs/ \
    mkdir -p /YEM_IRS943/ \
    mkdir -p /TPS_SPRDR1/ \
    mkdir -p /output/
    
COPY engine.tar /opt/exstream/

RUN tar -xf /opt/exstream/engine.tar -C /opt/exstream/8.0/ \
    mv -f /opt/exstream/8.0/Tar/* /opt/exstream/8.0/ \
    rmdir /opt/exstream/8.0/Tar/
    
COPY /TPS_SPRDR1/*.pub /opt/exstream/pubs/

COPY /YEM_IRS943/*.pub /opt/exstream/pubs/ 

LABEL io.k8s.description="Platform for building Spring Boot applications packaged with exstream" \
      io.k8s.display-name="Spring Boot Exstream builder 1.0" \
      io.openshift.expose-services="8080:http" \
      io.openshift.tags="builder,maven-3,gradle-2.6,springboot"