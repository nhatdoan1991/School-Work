
// Nhat Doan
// m8x1.v, a 8x1 multiplexor, gate synthesis
// compile: ~changw/ivl/bin/iverilog m8x1.v
// run: ./a.out

// Decoder function 3to8
module DecoderMod(s, o);
   input [2:0] s;
   output [0:7] o;
   wire [2:0] inv_s;

   not(inv_s[2], s[2]);
   not(inv_s[1], s[1]);
   not(inv_s[0], s[0]);

   and(o[0], inv_s[2], inv_s[1], inv_s[0]);
   and(o[1], inv_s[2], inv_s[1],     s[0]);
   and(o[2], inv_s[2],     s[1], inv_s[0]);
   and(o[3], inv_s[2],     s[1],     s[0]);
   and(o[4],     s[2], inv_s[1], inv_s[0]);
   and(o[5],     s[2], inv_s[1],     s[0]);
   and(o[6],     s[2],     s[1], inv_s[0]);
   and(o[7],     s[2],     s[1],     s[0]);

endmodule

//8x1 MUX
module MuxMod(s, d, o);
   input [2:0] s;
   input [0:7] d;
   output o;

   wire [0:7] s_decoded, and_out;

   DecoderMod my_decoder(s, s_decoded);

   and my8and [0:7] (and_out, d, s_decoded);

   or(o, and_out[0], and_out[1], and_out[2], and_out[3], and_out[4], and_out[5], and_out[6], and_out[7]);
endmodule

module TestMod;
   reg [2:0] s;
   reg [0:7] d;
   wire o;

   MuxMod my_mux(s, d, o);

   initial begin
      $display("Time  s..  d.......  o");
      $display("----  ---  --------  -");
      $monitor("%4d  %b  %b  %b", $time, s, d, o);
   end

	 always begin d[7] = 0; #1;   d[7] = 1; #1; end
         always begin d[6] = 0; #2;   d[6] = 1; #2; end
         always begin d[5] = 0; #4;   d[5] = 1; #4; end
         always begin d[4] = 0; #8;   d[4] = 1; #8; end
	 always begin d[3] = 0; #16;  d[3] = 1; #16; end
    	 always begin d[2] = 0; #32;  d[2] = 1; #32; end
   	 always begin d[1] = 0; #64;  d[1] = 1; #64; end
  	 always begin d[0] = 0; #128; d[0] = 1; #128; end
   	 always begin s[0] = 0; #256; s[0] = 1; #256; end
   	 always begin s[1] = 0; #512; s[1] = 1; #512; end
   	 always begin s[2] = 0; #1024; s[2] = 1; #1024; end

	 initial #2047 $finish;  	
endmodule
/* alternative to the long above: initial begin ... end
   always begin d[3] = 0; #1; d[3] = 1; #1; end
   always begin d[2] = 0; #2; d[2] = 1; #2; end
   always begin d[1] = 0; #4; d[1] = 1; #4; end
   always begin d[0] = 0; #8; d[0] = 1; #8; end
   always begin s[0] = 0; #16; s[0] = 1; #16; end
   always begin s[1] = 0; #32; s[1] = 1; #32; end
   initial #63 $finish;    // terminates after 63 cycles
*/
