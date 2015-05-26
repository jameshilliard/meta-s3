DESCRIPTION = "Cgminer bitcoin miner SW"
#LICENSE = "GPLv3 & bzip2"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
#LIC_FILES_CHKSUM = "file://COPYING"

DEPENDS = "ncurses curl udev"

SRCREV = "62573e7f57e986df50ee639ad4aef2f25e0f3174"
#PV = "${SRCREV}+git${SRCPV}"
#SRCREV = "4.9.1"
#PV = "${SRCREV}+${SRCPV}"
#PR = "r1"
SRC_URI = "git://github.com/ckolivas/cgminer.git;protocol=https;branch=master"
#SRC_URI	= "file://cgminer-3.8.5.tar.bz2"
#SRC_URI = "file://cgminer-3.12.0.tar.bz2"
#SRC_URI = "file://cgminer-4.6.1.tar.bz2"
#SRC_URI = "file://cgminer-4.8.0.tar.bz2"
#SRC_URI = "file://cgminer-3.4.3.tar.bz2"
S = "${WORKDIR}/git"
#S = "${WORKDIR}/cgminer-3.8.5"
#S = "${WORKDIR}/cgminer-3.12.0"
#S = "${WORKDIR}/cgminer-4.6.1"
#S = "${WORKDIR}/cgminer-4.8.0"
#S = "${WORKDIR}/cgminer-3.4.3"

#CFLAGS_prepend = "-I ${S}/compat/jansson-2.5/src -I ${S}/compat/libusb-1.0/libusb"
CFLAGS_prepend = "-I ${S}/compat/jansson-2.6/src -I ${S}/compat/libusb-1.0/libusb"
#CFLAGS_prepend = "-I ${S}/compat/jansson -I ${S}/compat/libusb-1.0/libusb"
#TARGET_LDFLAGS += -Wl,-rpath-link=$(STAGING_DIR)/usr/lib
#--with-sysroot=${prefix}/${TARGET_SYS}
#--with-system-libusb

EXTRA_OECONF = " \
		 --enable-ants2 \
	     --disable-adl \
	     --disable-opencl \
	     "
		 
do_configure_prepend() {
	autoreconf -fiv
}

do_compile_append() {
	make api-example
}

do_install_append() {
        install -d ${D}${bindir}
        install -m 0755 ${S}/api-example ${D}${bindir}/cgminer-api
}
 
inherit autotools pkgconfig