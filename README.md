Wordfreq is a simple REST client based on Newman which calculates word frequencies in responses.


OVERVIEW

App takes arbitrary number of arguments which are used as destination ids in requests to top10 api. Requests & responses are handled with modern HTTP client named Newman[1]. App creates sequence of futures (one for every request ~ dest_id) and waits for responses. Timeout is set to 20 sec. When received, description text of each response is stripped from json chars and used for calculation of word frequencies. If an error occurs during the process (response code differs from OK, response parsing fails, etc.) then no output is printed for this destination id. Algorithm for freq counting is case insensitive and removes unwanted chars like slashes, apostrophes, question marks, dots, etc. in order to achieve similar results like [2].


USAGE

Wordfreq uses sbt for deployment.

compile - builds application, acquires dependencies
run - run app, takes 0..N params as arguments
test - executes junit tests, could be extended with data from [2]
one-jar - creates executable jar


NOTES

[1] https://github.com/stackmob/newman

[2] http://www.writewords.org.uk/
