# QUBIC

---

## Link


| App Url                                     |
|---------------------------------------------|
| Wallet - https://wallet.qubic.li/           |
| Control - https://app.qubic.li/             |
| Client - https://github.com/qubic-li/client |

| Pool Url                 |
|--------------------------|
| https://ai.diyschool.ch/ |
| https://mine.qubic.li/   |

## Config 
### CPU appsettings.json
````json
{
    "Settings": {
        "baseUrl": "https://ai.diyschool.ch/",
        "amountOfThreads": 16,
        "payoutId": null,
        "accessToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6ImM5YTMxMTU1LWRiNTEtNDU3Yi05OGQ5LTQ5NmFjZDY3NjQ4OCIsIk1pbmluZyI6IiIsIm5iZiI6MTcwNDI4NDUwOSwiZXhwIjoxNzM1ODIwNTA5LCJpYXQiOjE3MDQyODQ1MDksImlzcyI6Imh0dHBzOi8vcXViaWMubGkvIiwiYXVkIjoiaHR0cHM6Ly9xdWJpYy5saS8ifQ.pucDKif-yI0w1WOZTgDu0qHQWTFs9gx43GPaP6cHCAvRlhTxmoiLaj4Dzs4RRTiymn6Q2C0JJdiHGxwald7GFA",
        "alias": "woogering.msi-7950x",
        "allowHwInfoCollect": true,
        "checkUpdateEnabled": true,
        "autoupdateEnabled": true,
        "logLevel": 3
    }
}
````
### GPU appsettings.json
````json
{
    "Settings": {
        "baseUrl": "https://mine.qubic.li/",
        "amountOfThreads": 1,
        "payoutId": null,
        "accessToken": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6ImIxODI3Yzg0LTA0ZGEtNDJkMi04MGNmLTIzMGNkYmI4MWRlNiIsIk1pbmluZyI6IiIsIm5iZiI6MTcwMzMzODU2MSwiZXhwIjoxNzM0ODc0NTYxLCJpYXQiOjE3MDMzMzg1NjEsImlzcyI6Imh0dHBzOi8vcXViaWMubGkvIiwiYXVkIjoiaHR0cHM6Ly9xdWJpYy5saS8ifQ.dtqTHkb4RSM6VSqLkinjlducuIsMjYmtoEJPJfwDpA6_qmpF70eLdD26__r49GBaJRxl47ehFEWcwhLHHS_7-A",
        "alias": "woogering.rog-gpu",
        "allowHwInfoCollect": true,
        "overwrites": {
            "CUDA": "12"
        },
        "checkUpdateEnabled": true,
        "autoupdateEnabled": true,
        "logLevel": 3
    }
}
````

## Script
### Use Proxy
````shell
export HTTPS_PROXY=http://127.0.0.1:7890
````
### Optimize GPU - power.sh
````shell
# control gpu require driver
nvidia-smi -pm 1
nvidia-smi -pl 180
# control fan require X11 Server 
nvidia-settings -a '[gpu:0]/GPUFanControlState=1'
nvidia-settings -a '[fan:0]/GPUTargetFanSpeed=85'
nvidia-settings -a '[fan:1]/GPUTargetFanSpeed=75'

````
### Client Clean - killq.sh
````shell
killall qli-Client
killall qli-runner
rm -f qli-runner
rm -f qli-runner.lock
rm -f flags.lock
ps aux|grep qli
````
### GPU Mine - run.sh
````shell
#!/bin/bash
# set proxy
export HTTPS_PROXY=http://192.168.50.30:8888
# add cuda environment variables
export PATH=/usr/local/cuda-12.0/bin${PATH:+:${PATH}}
export LD_LIBRARY_PATH=/usr/local/cuda-12.0/lib64${LD_LIBRARY_PATH:+:${LD_LIBRARY_PATH}}
# optimize gpu
sh power.sh
# start miner
echo 'start qli-Client'
echo '============================================='
cd /q
nohup ./qli-Client &
sleep 2
tail -f nohup.out
````
### CPU Mine - run.sh
````shell
#!/bin/bash
# set proxy
export HTTPS_PROXY=http://192.168.50.30:8888
# start miner
echo 'start qli-Client'
echo '============================================='
cd /q
nohup ./qli-Client &
sleep 2
tail -f nohup.out
````
  
  
  



