wget https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2204/x86_64/cuda-keyring_1.1-1_all.deb

dpkg -i cuda-keyring_1.1-1_all.deb

apt update

apt install cuda-toolkit-12-3 -y

apt install cuda-drivers-545 -y