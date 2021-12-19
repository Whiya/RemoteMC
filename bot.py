
import paramiko
import discord
from paramiko import server

pa = paramiko.SSHClient()
pa.set_missing_host_key_policy(paramiko.WarningPolicy())
pa.connect("localhost",username="root",password="Riyaya_mumei_1528")
cmds = "echo ba-ka"
stdin, stdout, stderr = pa.exec_command(cmds)
print(stdout.readlines())
pa.close()


TOKEN = "OTIyMTA4NDM0Mzc4MjcyNzc4.Yb8qZg.o1kZsZUpAfjHRpEbiIznyYGfpto"
client = discord.Client()

@client.event
async def on_ready():
    print("ログインしました")

@client.event
async def on_message(message):
    print("test")


@client.event
async def on_member_join(member):
    print("test")



client.run(TOKEN)