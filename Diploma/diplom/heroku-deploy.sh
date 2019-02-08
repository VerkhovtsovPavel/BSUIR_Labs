#!/bin/bash

git archive --format=tar.gz -o deploy.tgz $BITBUCKET_COMMIT

HEROKU_VERSION=$BITBUCKET_COMMIT

echo "Deploying Heroku Version $HEROKU_VERSION"

URL_BLOB=`curl -s -n -X POST https://api.heroku.com/apps/grapfol-net/sources \
-H 'Accept: application/vnd.heroku+json; version=3' \
-H 'Authorization: Bearer 9adb99a0-174c-4cdf-ac32-531c5ad0ad88'`

PUT_URL=`echo $URL_BLOB | python -c 'import sys, json; print(json.load(sys.stdin)["source_blob"]["put_url"])'`
GET_URL=`echo $URL_BLOB | python -c 'import sys, json; print(json.load(sys.stdin)["source_blob"]["get_url"])'`

curl $PUT_URL  -X PUT -H 'Content-Type:' --data-binary @deploy.tgz

REQ_DATA="{\"source_blob\": {\"url\":\"$GET_URL\", \"version\": \"$HEROKU_VERSION\"}}"

BUILD_OUTPUT=`curl -s -n -X POST https://api.heroku.com/apps/grapfol-net/builds \
-d "$REQ_DATA" \
-H 'Accept: application/vnd.heroku+json; version=3' \
-H "Content-Type: application/json" \
-H "Authorization: Bearer 9adb99a0-174c-4cdf-ac32-531c5ad0ad88"`

STREAM_URL=`echo $BUILD_OUTPUT | python -c 'import sys, json; print(json.load(sys.stdin)["output_stream_url"])'`

curl $STREAM_URL