// DQ4.v --  array of 4 DQ F/F
//
// Nhat Doan -  csc137
//SR module 
module SR(S,R,O);
input S,R;
wire Sout,Rout;
output O;

	nor(Sout,S,Rout);
	nor(Rout,R,Sout);
	assign #1 O = Rout;
endmodule
//DQ module
module DQ(D,en,Q);
input D, en;
wire S,R,Dnot;
output Q;

	and(S, D, en);
	not(Dnot,D);
	and(R,Dnot, en);
	SR mySR(S,R,Q);

endmodule
//DQ4 module
module DQ4(D,Q,en);
input [0:3] D;
input en;
output [0:3] Q;
	DQ myDQ1(D[0],en,Q[0]);
	DQ myDQ2(D[1],en,Q[1]);
	DQ myDQ3(D[2],en,Q[2]);
	DQ myDQ4(D[3],en,Q[3]);
endmodule
//TestMode Module
module TestMod;

	reg [0:3] D;
	reg en;
  	wire [0:3] Q;
	DQ4 myDQ4(D,Q,en);

   	initial begin
      		$monitor("%4d  %b  %b  %b", $time, D, en, Q);
      		$display("Time  D...  en Q...");
      		$display("----  ----  -  ----");
   	end

	always begin
      	#1; D = 4'b0000; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0001; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0010; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0011; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0100; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0101; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0110; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b0111; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1000; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1001; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1010; #1; en = 1; #1; en = 0; #1;
     	#1; D = 4'b1011; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1100; #1; en = 1; #1; en = 0; #1;
     	#1; D = 4'b1101; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1110; #1; en = 1; #1; en = 0; #1;
      	#1; D = 4'b1111; #1; en = 1; #1; en = 0; #1;
  	end
	initial #69 $finish;
endmodule

