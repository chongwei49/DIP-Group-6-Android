import csv

with open('Traits.csv') as f:
    reader = csv.reader(f, delimiter=',')
    line_count = 0
    a = "INSERT INTO traits (`quizCategory`, `personalityType`, `traitName`, `description`) VALUES\n"
    for row in reader:
        if line_count == 0:
            print("columns")
            line_count += 1
        else:
            # print(f"INSERT INTO questions VALUES({row[0]}, {row[1]}, {row[2]}, {row[3]}, {row[4]}, {row[5]})")
            a += f"('{row[1]}', '{row[2]}', '{row[3]}', '{row[4]}'),\n" #rmbr to remove the last comma before running the code in sql
    a += ";"
    print(a)