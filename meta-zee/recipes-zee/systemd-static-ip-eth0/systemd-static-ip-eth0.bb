#
SUMMARY = "Static IP for eth0 on systemd"

# Install to:
# /etc/systemd/network/20-wired.network
# SECTION = "network"
# LICENSE = "BSD"

SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Not working, see -image.bb, ROOTFS_POSTPROCESS_COMMAND
# SYSTEMD_SERVICE_${PN} = "wpa_supplicant@wlan0.service"
# SYSTEMD_AUTO_ENABLE = "enable"

SRC_URI += "file://20-wired.network \
           "

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/systemd/network
    install -m 777 20-wired.network ${D}${sysconfdir}/systemd/network
}
