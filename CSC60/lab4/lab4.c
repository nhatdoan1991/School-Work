/*-------------------------------------------------*/
/* Nhat Doan                                       */
/* Lab 4                                           */
/* Figure the perimeter and area of a polygon      */
/* surrounded by a circle                          */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

//#define FILE_IN lab4.dat
#define INFILE "lab4sample.dat"


int main(void)
{

	double radius, numberOfSize, perimeter, area,sinResult;
	FILE * input_file;
	FILE * output_file;
	input_file=fopen(INFILE,"r");
	if(input_file==NULL)
	{
		printf("Error on opening the data file\n");
		exit (EXIT_FAILURE);
	}
	output_file=fopen("lab4.out","w");
	if(output_file==NULL)
	{	
		printf("Error on opening the output file\n");
		exit (EXIT_FAILURE);
	}
	


	fprintf(output_file, "\nNhat Doan.  Lab 4.\n\n");
	fprintf(output_file, "            Number      Perimeter      Area Of  \n");
	fprintf(output_file, " Radius    Of Sides    Of Polygon      Polygon  \n");
	fprintf(output_file, "--------   --------   ------------   ----------- \n");

	while((fscanf(input_file,"%lf%lf",&radius,&numberOfSize))==2)
	{	
		perimeter= 2*radius*numberOfSize*sin(M_PI/numberOfSize);
		area=0.5*numberOfSize*radius*radius*sin(2*M_PI/numberOfSize);
		fprintf(output_file,"%7.2f%11.2f%15.4f%14.4f\n",radius,numberOfSize,perimeter,area);		
	}
	fclose(input_file);
	fclose(output_file);
	return EXIT_SUCCESS;
	
}
		
		
		



/*------------------------------------------------------------------*/
