# SortEST
SortEST is program that sorts few files and creates single file with sorted data. The work was done as a test task for the Financial Technology Center.

РУССКИЙ:

Параметры запуска:
	
	1. Режим сортировки (по умолчанию -a):
		-a - по возрастанию
		-d - по убыванию

	2. Тип данных (обязательный):
		-s - строки
		-i - целые числа
	
	3. Имя получаемого файла (обязательный):
		Some_new_file_name.txt

	4. Имена входных файлов (не менее одного):
		Some_old_file1_name.txt
		Some_old_file2_name.txt
	5. Режим записи в файл (по умолчанию -f):
		-t - добавлять в файл
		-f - перезаписывать файл

Примеры запуска из командной строки Windows:
 
	1. SortEST.exe -i -a out.txt in.txt (для целых чисел по возрастанию)
	2. SortEST.exe -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)

=================================================================================================================================

ENGLISH:

Launch Parameters:

	1. Sorting mode (default -a):
		-a - in ascending order
		-d - in descending order

	2. Data type (necessarily):
		-s - string
		-i - integer
	
	3. New file name (necessarily):
		Some_new_file_name.txt

	4. Name of the file used (one or more):
		Some_old_file1_name.txt
		Some_old_file2_name.txt
	5. Writing mode (default -f):
		-t - append
		-f - rewrite file

Windows Command Line Startup Examples:
 
	1. SortEST.exe -i -a out.txt in.txt (for ascending integers)
	2. SortEST.exe -s out.txt in1.txt in2.txt in3.txt (for ascending strings)

=================================================================================================================================

Java Development Kit version 17.0.2

Maven version 3.8.1

