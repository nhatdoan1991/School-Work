//Nhat Doan
//adder.v, 137 verilog Programming Assignment #3

module TestMod;

	parameter STDIN = 32'h8000_0000;// I/O address of keyboard input channe

	reg [7:0] str [1:3]; // typing in 2 chars at a time (decimal # and Enter key)
	reg [4:0] X, Y;      // 5-bit X, Y to sum
	wire [4:0] S;        // 5-bit Sum to see as result
	wire C5;             // like to know this as well from result of adder

	BigAdder my_adder(X,Y,S,C5);
	
	initial begin
		// prompt to input X and convert to decimal value for X 
		$display("Enter X: ");
		str[1] = $fgetc(STDIN);
		str[2] = $fgetc(STDIN);
		str[3] = $fgetc(STDIN);	
		X=(str[1]-48)*10 + str[2] - 48;
		// prompt to input Y and convert to decimal value for Y
		$display("Enter Y: ");
		str[1]= $fgetc(STDIN);
		str[2]= $fgetc(STDIN);
		str[3]= $fgetc(STDIN);
		Y=(str[1]-48)*10 + str[2] - 48;

		//wait for adder gets them processed 
		#1;
		$display("X = %d (%b)  Y = %d (%b)",X, X, Y, Y);
		$display("Result is %d (%b )and  C5 is %b",S,S, C5 );
	end 
endmodule

module BigAdder(X,Y,S,C5);
	input [4:0] X,Y;
	output [4:0] S;
	output C5;
	
	wire C1,C2,C3,C4;
	//C0 carrying bit 0 is 0	
	FullAdderMod bit0(X[0],Y[0],0,S[0],C1);
	FullAdderMod bit1(X[1],Y[1],C1,S[1],C2);
	FullAdderMod bit2(X[2],Y[2],C2,S[2],C3);
	FullAdderMod bit3(X[3],Y[3],C3,S[3],C4);
	FullAdderMod bit4(X[4],Y[4],C4,S[4],C5);
endmodule

module FullAdderMod(X,Y,Cin,S,Cout);

	input X,Y, Cin;
	output S,Cout;

	wire sumXY, andSumXY_Cin, andXY;

	xor(sumXY,X,Y);
	xor(S,sumXY,Cin);
	and(andSumXY_Cin,sumXY,Cin);
	and(andXY,X,Y);
	or(Cout,andSumXY_Cin,andXY);

endmodule

