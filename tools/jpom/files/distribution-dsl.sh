# scriptId 可以是项目路径下脚本文件名或者系统中的脚本模版ID
description: 生产
run:
  start:
#    scriptId: project.sh
    scriptId: 2d9b54bb8b6f4776a778c96627850b40
    scriptArgs: start
    scriptEnv:
      "boot_active": test
  status:
#    scriptId: project.sh
    scriptId: 2d9b54bb8b6f4776a778c96627850b40
    scriptArgs: status
  stop:
#    scriptId: project.sh
    scriptId: 2d9b54bb8b6f4776a778c96627850b40
    scriptArgs: stop
#  restart:
##    scriptId: project.sh
#    scriptId: eb16f693147b43a1b06f9eb96aed1bc7
#    scriptArgs: restart
#    scriptEnv:
#      "boot_active": test
file:
# 备份文件保留个数
#  backupCount: 5
# 限制备份指定文件后缀（支持正则）
#  backupSuffix: [ '.jar','.html','^.+\.(?i)(txt)$' ]
config:
# 是否开启日志备份功能
#  autoBackToFile: true