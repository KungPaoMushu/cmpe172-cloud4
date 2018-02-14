# HW1: Ansible

### Instructions:
- Configure Ansible to deploy webserver, and bring it up a port 80 with a web page that is publically accessible that displays the message: “Hello World”.
- Include in the Ansible playbook, plays to deploy and un-deploy the resources

### Steps:
- Setup AWS account and create a EC2 instance with basic following specs:
  - **AMI**: Ubuntu Server 16.04 LTS (HVM), SSD Volume Type - ami-965e6bf3
  - **Instance Type**: t2.micro (General purpose)
  - **Number of instances**: 1 (In Step 3)
  - **In Step 6 (Configure Security Group)**: 
    - **Add Rule 1**: *SSH, TCP, Port 22, Source: Anywhere/MyIp*
    - **Add Rule 2**: *HTTP, TCP, Port 80, Source: Anywhere*
  - Once the instances is being created, explore the ***Network & Security*** section and click on ***Key Pairs***
    - In here, click on ***Create Key Pair***, and define any name, let's say: ***ansible-server***
  - Plce the downloaded pem file from aws in a desidered directory
    - It is recommended to run the command below to avoid unauthorized writes on the rsa key

      $chmod 400 <fileName>.pem  

### Testing the instance connection through SSH
- Open the terminal and supposed that the .pem file is stored in the home directory. (In this step, find the EC2 public ip located in the intance's dashboard under ***description***
In our case, *Public IP* is ***18.216.175.136***), and run:

      $ssh -i ansible-server.pem ubuntu@18.216.175.136
      
Expected results:
![Alt text](/ansible-hw/screenshots_images/success-ec2.png?raw=true "Image")

- input **exit** within the sss EC2 console to quit

### Install Ansible
- In the terminal, run:
    $sudo apt-get install ansible
    
### Configure Ansible
- Create a new file called ***hosts*** via:
      touch hosts
- Use ***nano*** or your prefered text editor to add some information to ***hosts***

      $nano hosts
  Add:
  
      [mywebseervers]
      18.216.175.136 ansible_ssh_private_key_file=<path>/ansible-server.pem
      
- Test the connection between EC2 and Ansible with the command:
 
      $ansible -i hosts all -m ping -s -u ubuntu
      
Expected results:

![Alt text](/ansible-hw/screenshots_images/success-ansible-ec2.png?raw=true "success")


- Create a new file called ***sites.yml***

      $touch sites.yml   
      $nano sites.yml   
      ---   
      - hosts: mywebserver
        become: yes
        remote_user: ubuntu
        gather_facts: no
        
        tasks:
            - name: 'updates'
              raw: sudo apt-get update
            - name: Nginx setup
              become: yes
              apt: pkg=nginx state=installed update_cache=true
            - name: index.html copy
              template: src=index.html.j2 dest=/usr/share/nginx/html/index.html
              

- Create a new file ***index.html.j2***

      $touch index.html.j2
      $nano index.html.j2
      
- To see content go to: ***index.html.j2*** above

### Test the Ansible playbook
- Run:
      ansible-playbook -i hosts -s -u ubuntu sites.yml
      
Expected results:

![Alt-text](/ansible-hw/screenshots_images/success-playbook.png)

*Note that the initial run of the command above could produce an output *change=2*

- Go to your browser and navigate ***http://18.216.175.136***

Expected results:
![Alt text](/ansible-hw/screenshots_images/success-nginx.png)

### Routing customized HTML in the Nginx server
- Run
      ssh -i ansible-server.pem ubuntu@18.216.175.136
      
- Once the connection is being established, run:
  
      sudo nano /etc/nginx/sites-available/default

- Look for the line

      root /var/www/html;
      
- Replace with:

      root /usr/share/nginx/html;
      
- Find:

      location / {
           try_files $uri $uri/ =404;
           autoindex on; # add this new line
      }
      
- Save the changes and restart the nginx server with the command:

      sudo service nginx restart

- Go to your browser and navigate ***http://18.216.175.136***

Expected results:
![Alt txt](/ansible-hw/screenshots_images/success-nginx-html.png)

- Done! Ansible playbook is deployed and the html is visible under port 80!

### Un-deploy Server
- Run the following command:

      ansible-playbook -i hosts -s -u ubuntu undeploy.yml
      
 
