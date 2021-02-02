kill -9 $(ps aux | grep 'demo' | awk '{print $2}')
sleep 5
echo "Process killed"
echo "Process Restarting"
nohup java -jar target/demo-0.0.1-SNAPSHOT.jar & > log.out
ps aux | grep 'demo' 