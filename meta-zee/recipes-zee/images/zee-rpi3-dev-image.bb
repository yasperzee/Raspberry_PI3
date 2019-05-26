#*******************************************************************************
# TODO: LLVM / CLANG tools
# TODO: RUST language
# yasperzee v0.1    3'19   GNU c/c++ development tools
#
#*******************************************************************************
require zee-rpi3-base-image.bb
export IMAGE_BASENAME = "zee-rpi3-dev-image"
SUMMARY = "32bit development image for Rpi3"
#*******************************************************************************
# PACKAGE_EXCLUDE = " "

# verify is following in image via 'inherit IMAGE'
# findutils, sysutils
DEV_SDK_INSTALL = " \
    coreutils \
    binutils \
    diffutils \
    elfutils \
    binutils-symlinks \
    elfutils-binutils \
    cpp \
    cpp-symlinks \
    file \
    g++ \
    g++-symlinks \
    gcc \
    gcc-symlinks \
    gdb \
    gdbserver \
    gettext \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    ltrace \
    make \
    pkgconfig \
    strace \
"

IMAGE_INSTALL += " \
    ${DEV_SDK_INSTALL} \
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
