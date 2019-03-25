#*******************************************************************************
#
#   The most up-stream image, inherited by all "zee" images.
#   In best case scenario, all changes when integrating new poky, happens here.
#
# TODO:         u-boot
# TODO:         Bluetooth
# TODO:         64-bit ( Branch )
#
# yasperzee v0.2    3'19    sysvinit --> systemd			
#                   - Static ip address for eth0
#                   - Headless system:  Autostart for wlan0 on 1. boot and
#                                       wpa_supplicant for systemd.
#
# yasperzee v0.1    3'19    THUD integration, "plan B" i.e sysvinit.
# FIXME:    wifi connects to mobileAP, but not to homeAP,
#           check wpa_supplicant configuration vs router.
#                   - Use console-image by jumpnowtek as reference
#                   - Minimal set of recipes on IMAGE_INSTALL
#                   - Build image with SysVinit
#
#*******************************************************************************
LICENSE  = "MIT"
inherit image
export IMAGE_BASENAME = "zee-rpi3-base-image"
SUMMARY = "32bit minimal image for Rpi3"
#*******************************************************************************
IMAGE_FEATURES += " splash"
SPLASH = "psplash-raspberrypi"
DEPENDS += "bcm2835-bootfiles"
COMPATIBLE_MACHINE = "^rpi$"
IMAGE_LINGUAS   = " "

# PACKAGE_EXCLUDE = " "

ZEE_STUFF = " \
   systemd-static-ip-eth0 \
   systemd-headless-wlan0-setup \
"
#
#   systemd-static-ip-wlan0 \
#

CORE_OS = " \
    kernel-modules \
    packagegroup-core-boot \
    tzdata \
"
WIFI_SUPPORT = " \
    crda \
    iw \
    wpa-supplicant \
    linux-firmware-raspbian \
"
NETWORK_TOOLS = " \
    ethtool \
    iperf3 \
    iproute2 \
    iptables \
    netcat-openbsd \
    nmap \
    ntp ntp-tickadj \
    tcpdump \
    firewall \
"
SSH_SUPPORT = " \
    openssh \
    openssh-keygen \
    openssh-sftp-server \
"
TOOLS_INSTALL = " \
    grep \
    less \
    nano \
    util-linux \
    rng-tools \
    rndaddtoentcnt \
    python3 \
"

IMAGE_INSTALL += " \
    ${ZEE_STUFF} \
    ${CORE_OS} \
    ${WIFI_SUPPORT} \
    ${NETWORK_TOOLS} \
    ${SSH_SUPPORT} \
    ${TOOLS_INSTALL} \
"

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND += " \
set_local_timezone ; \
disable_bootlogd ; \
"

# systemd: autostart wpa_suppicant@wlan0 on first boot ( headless system)
#autorun_wlan0() {
#   ln -sf ${IMAGE_ROOTFS}/etc/systemd/system/wpa_supplicant@wlan0.service \
#   ${IMAGE_ROOTFS}/etc/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
#}
