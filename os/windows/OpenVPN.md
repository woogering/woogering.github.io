# OpenVPN 安装双网卡

目前已知2.5.8的安装文件是包含创建虚拟网卡的可执行文件 tapctl.exe

## 1.1 创建网卡
"C:\Program Files\OpenVPN\bin\tapctl.exe" create --hwid root\tap0901

## 1.2 配置网卡
修改ovpn文件

配置文件1    dev-node OpenVPN-TAP-1

配置文件2    dev-node OpenVPN-TAP-2
