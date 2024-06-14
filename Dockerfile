FROM 477873957424.dkr.ecr.us-east-1.amazonaws.com/base_app_java:17-jre-alpine
LABEL name=ms-visa-coupons

RUN apk add --no-cache bash
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
RUN mkdir -p /opt/ms-visa-coupons && chown javauser:javauser /opt/ms-visa-coupons

WORKDIR /opt/ms-visa-coupons
USER javauser

COPY /target/*.jar /opt/ms-visa-coupons/app.jar

CMD ["java", "-jar", "app.jar"]