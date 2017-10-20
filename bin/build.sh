#!/bin/bash

( cd map-server ; npm --no-bin-links install )
( cd map-server ; lein cljsbuild once )
docker build -t map-server .
