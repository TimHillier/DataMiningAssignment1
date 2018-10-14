#just to Generate large test datasets for the assignment. 
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
        f.write(str(i) + "    ")
        leng = random.randint(1,lines/3)
        x = random.randint(1,leng)
        f.write(str(leng-x+1)+"    ")
        for a in range(x,leng+1):
            #p = random.randint(1,lines/2)
            f.write(str(a) + " ")
        f.write("\n")
    f.close

def main():
    In()
    Create()
    print("Done")
main()