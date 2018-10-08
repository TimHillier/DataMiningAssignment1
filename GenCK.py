#creates a CK for the first part of the asignment. 
import random
name = "T"
lines = 101


def In():
    global name,lines
    name = raw_input("Name of file?")
    lines = int(input("How many Lines?"))


def Create():
    #create and fill the file. 
    f = open(name + ".txt", "w+")
    f.write(str(lines) + "\n")
    for i in range(lines):
        f.write(str(i+1) + "    ")
        leng = random.randint(1,lines/3)
        f.write(str(leng)+"    ")
        for a in range(leng):
            p = random.randint(1,lines/2)
            f.write(str(p) + " ")
        f.write("\n")
    f.close

def main():
    In()
    Create()
    print("Done")
main()