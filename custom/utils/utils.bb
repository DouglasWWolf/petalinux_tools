SUMMARY = "Install the utils folder"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
  
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://pcireg file://asroot file://remount"

FILES:${PN} += "/utils" 
  
S = "${WORKDIR}"
  
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"


do_install() {
   install -m 755 -d ${D}/utils
   install -m 4755 ${WORKDIR}/pcireg  ${D}/utils
   install -m 755  ${WORKDIR}/asroot  ${D}/utils
   install -m 755  ${WORKDIR}/remount ${D}/utils
}

# Inhibit warnings about files being stripped
INSANE_SKIP:${PN} += "already-stripped"

