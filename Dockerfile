FROM openjdk:17
RUN mkdir /app
COPY target/com/sloths/movie_review_project /app
WORKDIR /app
CMD java MovieReviewProjectApplication