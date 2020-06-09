DESCRIPTION = "A minimal image capable of update using ostree and OCI containers"

IMAGE_FEATURES += "ssh-server-dropbear"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_FSTYPES += "ext4"
IMAGE_OVERHEAD_FACTOR = "2"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    ostree \
    virtual/runc \
    busybox-udhcpc \
    fullmetalupdate \
    fullmetalusers \
"

IMAGE_FEATURES_REPLACES_ssh-server-openssh = "ssh-server-dropbear"

DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = ""

PACKAGECONFIG_remove-pn-qtbase  = "x11 xcb xkb xkbcommon-evdev "

WKS_FILES ?= "fullmetalupdate-${MACHINE}.wks.in"

# Set root password
# Use `openssl passwd <password>` to get the hash result of a password
# Current password is 'root'
ROOT_PASSWORD = "LlNBpbQmj8p7g"
EXTRA_USERS_PARAMS = "usermod -p ${ROOT_PASSWORD} root;"

inherit core-image
inherit fullmetalupdate_push_image_to_ostree
inherit extrausers
