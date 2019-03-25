#
SUMMARY = "Start & connect wlan0 on boot on systemd"

# install correct wpa_supplicant.conf to /etc/wpa_supplicant
# create templates and install:
# /etc/systemd/network/25-wireless.network
# /etc/systemd/system/wpa_supplicant@wlan0.service:
# SECTION = "network"
# LICENSE = "BSD"

SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


# Not working, see -image.bb, ROOTFS_POSTPROCESS_COMMAND
# SYSTEMD_SERVICE_${PN} = "wpa_supplicant@wlan0.service"
# SYSTEMD_AUTO_ENABLE = "enable"

SRC_URI += "file://25-wireless.network \
            file://wpa_supplicant.conf-mir \
            file://wpa_supplicant@wlan0.service \
           "

S = "${WORKDIR}"

do_install() {
		 install -d ${D}${sysconfdir}/wpa_supplicant
		 install -m 777 wpa_supplicant.conf-mir ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant.conf
		 install -d ${D}${sysconfdir}/systemd/network
		 install -m 777 25-wireless.network ${D}${sysconfdir}/systemd/network
		 install -d ${D}${sysconfdir}/systemd/system
		 install -m 777 wpa_supplicant@wlan0.service ${D}${sysconfdir}/systemd/system
}
