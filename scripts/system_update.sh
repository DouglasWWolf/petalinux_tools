src_dir=$1
dst_dir=$2
rv=release_version


# Compares a file between the src and dst folders
compare_file()
{
    src_file=$1
    dst_file=$2
    test -z $dst_file && dst_file=$src_file
    test -f ${dst_dir}/$dst_file || return 1
    cmp -s ${src_dir}/$src_file ${dst_dir}/$dst_file
    return $?
}


# Assume for a moment that we won't require a reboot at the end
need_reboot=0

if [ -z $src_dir ]; then 
    echo "No source folder specified for $0"
    exit 1
fi

if [ -z $dst_dir ]; then
    echo "No source folder specified for $0"
    exit 1
fi

if [ ! -d $src_dir ]; then
    echo "Invalid folder $src_dir for $0"
    exit 1
fi

if [ ! -d $dst_dir ]; then
    echo "Invalid folder $dst_dir for $0"
    exit 1
fi

if [ ! -f $src_dir/$rv ]; then
    echo "$0 could not find $src_dir/$rv"
    exit 1
fi

file=$rv
compare_file $file
if [ $? -ne 0 ]; then
    echo "Copying $file to $dst_dir"
    cp $src_dir/$file $dst_dir
    need_reboot=1
fi


file=boot.scr
compare_file $file
if [ $? -ne 0 ]; then
    echo "Copying $file to $dst_dir"
    cp $src_dir/$file $dst_dir
    need_reboot=1
fi


file=BOOT.BIN
compare_file $file
if [ $? -ne 0 ]; then
    echo "Copying $file to $dst_dir"
    cp $src_dir/$file $dst_dir
    need_reboot=1
fi


file=image.ub
compare_file $file
if [ $? -ne 0 ]; then
    echo "Copying $file to $dst_dir"
    cp $src_dir/$file $dst_dir
    need_reboot=1
fi

test $need_reboot -eq 1 && reboot now



