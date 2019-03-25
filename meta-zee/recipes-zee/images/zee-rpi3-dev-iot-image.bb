#*******************************************************************************
#
# yasperzee v0.1    3'19    mosquitto server
#                           mqtt development tools & clients
#                           inherit GNU tools (GCC and stuff)
#
#*******************************************************************************
LICENSE  = "MIT"
require zee-rpi3-dev-image.bb
export IMAGE_BASENAME = "zee-rpi3-dev-iot-image"
SUMMARY = "32bit IoT / MQTT image for Rpi3 with GNU tool chain"
#*******************************************************************************
# PACKAGE_EXCLUDE = " "
#
#ZEE_STUFF = " \
#   systemd-headless-mosquitto-start-onboot \
#"
MOSQUITTO = " \
    mosquitto \
"
MQTT_DEV = " \
    libmosquitto1 \
    libmosquittopp1 \
    mosquitto-dev \
"
MQTT_CLIENT = " \
    mosquitto-clients \
    python-paho-mqtt \
"

IMAGE_INSTALL += " \
    ${MOSQUITTO} \
    ${MQTT_DEV} \
    ${MQTT_CLIENT} \
"
#
#disable_unused_services() {
#    rm ${IMAGE_ROOTFS}/etc/rc5.d/S15mountnfs.sh
#}
#
#ROOTFS_POSTPROCESS_COMMAND += " \
#    disable_unused_services ; \
# "
#
