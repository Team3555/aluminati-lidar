#!/bin/sh

sudo cp ../services/aluminatilidar.service /etc/systemd/system
sudo systemctl enable aluminatilidar.service
