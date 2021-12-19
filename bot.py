import sys
import yaml
import configparser
import paramiko
import discord
from paramiko import server


#server作成（登録）
def server_create(msg,msg_id):
    name = msg[2]
    adress = msg[3]
    user = msg[4]
    paswd = msg[5]
    #config設定
    configs(name,adress,user,paswd)
    #設定終わり
    #設定完了メッセージ

    
#サーバを削除
def server_delete(msg,msg_id):
    name = msg[3]
    

    #おわり



def servers():
    #色々
    #コマンド、設定変更、status
    print("test")


def configs(server_name,adress,user,passwd):
    config = configparser.ConfigParser()
    config[server_name] = {
        "adress" : adress,
        'user': user,
        "passwd" : passwd
    }
    with open("/home/remote_mc/servers/"+server_name+".ini", 'w') as file:
        config.write(file)



#pa = paramiko.SSHClient()
#pa.set_missing_host_key_policy(paramiko.WarningPolicy())
#pa.connect("localhost",username="root",password="Riyaya_mumei_1528")
#cmds = "echo ba-ka"
#stdin, stdout, stderr = pa.exec_command(cmds)
#print(stdout.readlines())
#pa.close()

# 設定コマンドの作成
# prefix   rm!
# サーバー名 アドレス　ユーザー名　パスポート
# 
# rm! [サーバー名] start 

TOKEN = ""
client = discord.Client()

#bot起動時イベント
@client.event
async def on_ready():
    print("ログインしました")

#メッセージイベント
@client.event
async def on_message(message):
    #prefix確認
    if message.content.startswith("rm!"):
        #bot確認
        if client.user != message.author:
            msg = message.content.split(" ")
            ls = len(msg)
            #第二引数確認 作成、削除、サーバー名（その他）
            if msg[1] == "create":
                server_create(msg,message.id)
            elif msg[2] == "delete":
                server_delete(msg,message.id)
            else:
                #サーバー名の場合
                #サーバー関数の中で色々
                #servers()
                print("test")
            
            await message.channel.send("uwei")
            

#メンバーが入ったときのイベント  
@client.event
async def on_member_join(member):
    print("test")

client.run(TOKEN)

#server操作関係





