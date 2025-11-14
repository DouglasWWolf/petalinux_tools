SUMMARY = "Install the utils folder"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
  
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://pcireg        \
           file://asroot        \
           file://sim           \
           file://remount       \
           file://iotool        \
           file://log           \
           file://radlogger.arm \
           file://llnse.arm"

FILES:${PN} += "/utils" 
  
S = "${WORKDIR}"
  
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"


do_install() {
   install -m 755 -d                        ${D}/utils
   install -m 4755 ${WORKDIR}/pcireg        ${D}/utils
   install -m 755  ${WORKDIR}/asroot        ${D}/utils
   install -m 755  ${WORKDIR}/sim           ${D}/utils
   install -m 755  ${WORKDIR}/remount       ${D}/utils
   install -m 4755 ${WORKDIR}/llnse.arm     ${D}/utils
   install -m 755  ${WORKDIR}/iotool        ${D}/utils      
   install -m 755  ${WORKDIR}/radlogger.arm ${D}/utils
   install -m 755  ${WORKDIR}/log           ${D}/utils
}

# Inhibit warnings about files being stripped
INSANE_SKIP:${PN} += "already-stripped"

