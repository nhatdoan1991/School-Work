/*
CSC139 
Spring 2021
First Assignment
Last Name, First Name : Nhat Doan
Section # 04
OSs Tested on: Linux with ECS account through Putty
Execution: in the folder contain consumer.cand producer.c 
compile each file  with: gcc producer.c -lrt -o producer
						gcc consumer.c -lrt -o consumer
then run the executable file : ./producer #ofBufSize #ofItem seed
Test case 1: bufSize < itemCnt : ./producer 10 20 2
Test case 2: bufSize >>> itemCnt : ./producer 100 900 2
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <unistd.h>
#include <sys/types.h>

// Size of shared memory block
// Pass this to ftruncate and mmap
#define SHM_SIZE 4096

// Global pointer to the shared memory block
// This should receive the return value of mmap
// Don't change this pointer in any function
void* gShmPtr;

// You won't necessarily need all the functions below
void Producer(int, int, int);
void InitShm(int, int);
void SetBufSize(int);
void SetItemCnt(int);
void SetIn(int);
void SetOut(int);
void SetHeaderVal(int, int);
int GetBufSize();
int GetItemCnt();
int GetIn();
int GetOut();
int GetHeaderVal(int);
void WriteAtBufIndex(int, int);
int ReadAtBufIndex(int);
int GetRand(int, int);


int main(int argc, char* argv[])
{
        pid_t pid;
        int bufSize; // Bounded buffer size
        int itemCnt; // Number of items to be produced
        int randSeed; // Seed for the random number generator 

        if(argc != 4){
                        printf("Invalid number of command-line arguments\n");
                        exit(1);
        }
	bufSize = atoi(argv[1]);
	itemCnt = atoi(argv[2]);
	randSeed = atoi(argv[3]);
	
	// Write code to check the validity of the command-line arguments
        if(bufSize > 800||bufSize<2)
        {
                fprintf(stderr,"Invalid Buffer Size \n");
                exit(1);
        }
        if(itemCnt < bufSize )
        {
                printf("Test case 1 with  Item Count < bufSize \n");
        }else{
		printf("Test case 2 with  Item Count > bufSize \n");
	}

         // Function that creates a shared memory segment and initializes its header
        InitShm(bufSize, itemCnt);        

	/* fork a child process */ 
	pid = fork();

	if (pid < 0) { /* error occurred */
		fprintf(stderr, "Fork Failed\n");
		exit(1);
	}
	else if (pid == 0) { /* child process */
		printf("Launching Consumer \n");
		execlp("./consumer","consumer",NULL);
	}
	else { /* parent process */
		/* parent will wait for the child to complete */
		printf("Starting Producer\n");
		
		// The function that actually implements the production
        Producer(bufSize, itemCnt, randSeed);
		
		printf("Producer done and waiting for consumer\n");
		wait(NULL);		
		printf("Consumer Completed\n");
	}
    
    return 0;
}


void InitShm(int bufSize, int itemCnt)
{
    int in = 0;
    int out = 0;
	const char *name = "OS_HW1_nhat_doan"; // Name of shared memory object to be passed to shm_open

     // Write code here to create a shared memory block and map it to gShmPtr
	 // Use the above name.
	 // **Extremely Important: map the shared memory block for both reading and writing 
	 // Use PROT_READ | PROT_WRITE
	int shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);
        ftruncate(shm_fd, SHM_SIZE);
        gShmPtr = mmap(0, SHM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd,0);
	// Write code here to set the values of the four integers in the header
        // Just call the functions provided below, like this
        SetBufSize(bufSize); 	
	//printf(" bufSize = %d\n",bufSize);
        SetItemCnt(itemCnt);
        SetIn(in);
        SetOut(out);	   
}

void Producer(int bufSize, int itemCnt, int randSeed)
{
    int in = 0;
    int out = 0;
    int i = 1;    
    srand(randSeed);

    // Write code here to produce itemCnt integer values in the range [0-2500]
   
    // Use the functions provided below to get/set the values of shared variables "in" and "out"
    // Use the provided function WriteAtBufIndex() to write into the bounded buffer 	
	// Use the provided function GetRand() to generate a random number in the specified range
    // **Extremely Important: Remember to set the value of any shared variable you change locally
	// Use the following print statement to report the production of an item:
	// printf("Producing Item %d with value %d at Index %d\n", i, val, in);
	// where i is the item number, val is the item value, in is its index in the bounded buffer
    	while(i <= itemCnt){
                while((GetIn() +1)%bufSize == GetOut())
                {
		//printf("test: Producer is doing nothing");
                // do nothing and wait for the child process Consumer to consume items that are loaded to the shared memory buffer
		}
                 
                
                //write value randommed from 0 to 2500 into buffer
                int val = GetRand(0,2500);
		WriteAtBufIndex(in,val);
                printf("Producing Item %d with value %d at Index %d\n", i, val, in);
               	i++;//increment item count
		 //increment the in value by +1. Mod bufSize because the in value may exceed bufsize
                in = (in+1)%bufSize;
                //update the in value in shared memory so the system is synchonized
                SetIn(in);
        }

	printf("Producer Completed\n");
}

// Set the value of shared variable "bufSize"
void SetBufSize(int val)
{
        SetHeaderVal(0, val);
}

// Set the value of shared variable "itemCnt"
void SetItemCnt(int val)
{
        SetHeaderVal(1, val);
}

// Set the value of shared variable "in"
void SetIn(int val)
{
        SetHeaderVal(2, val);
}

// Set the value of shared variable "out"
void SetOut(int val)
{
        SetHeaderVal(3, val);
}

// Get the ith value in the header
int GetHeaderVal(int i)
{
        int val;
        void* ptr = gShmPtr + i*sizeof(int);
        memcpy(&val, ptr, sizeof(int));
        return val;
}

// Set the ith value in the header
void SetHeaderVal(int i, int val)
{
// Write the implementation
	void* ptr = gShmPtr + i*sizeof(int);
        memcpy(ptr, &val, sizeof(int)); 

}

// Get the value of shared variable "bufSize"
int GetBufSize()
{       
        return GetHeaderVal(0);
}

// Get the value of shared variable "itemCnt"
int GetItemCnt()
{
        return GetHeaderVal(1);
}

// Get the value of shared variable "in"
int GetIn()
{
        return GetHeaderVal(2);
}

// Get the value of shared variable "out"
int GetOut()
{             
        return GetHeaderVal(3);
}


// Write the given val at the given index in the bounded buffer 
void WriteAtBufIndex(int indx, int val)
{
        // Skip the four-integer header and go to the given index 
        void* ptr = gShmPtr + 4*sizeof(int) + indx*sizeof(int);
	    memcpy(ptr, &val, sizeof(int));
}

// Read the val at the given index in the bounded buffer
int ReadAtBufIndex(int indx)
{
// Write the implementation
 
	int valToRead;
	void* ptr = gShmPtr + 4*sizeof(int) + indx*sizeof(int);//skip first 4 interger header 
        memcpy(&valToRead, ptr, sizeof(int));
        return valToRead;
}

// Get a random number in the range [x, y]
int GetRand(int x, int y)
{
	int r = rand();
	r = x + r % (y-x+1);
    return r;
}
