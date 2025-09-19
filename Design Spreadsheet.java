class Spreadsheet {
    private int rows;
    private int[][]sheet;


    public Spreadsheet(int rows)
    {
        this.rows=rows;
        sheet=new int[rows+1][26];
    }
    
    public void setCell(String cell, int value) 
    {
        int col=cell.charAt(0)-'A';
        int row=Integer.parseInt(cell.substring(1));
        sheet[row][col]=value;
    }
    
    public void resetCell(String cell)
    {
         int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1));
        sheet[row][col] = 0;
    }
    
    public int getValue(String formula) {
          // formula like "=X+Y"
        String f = formula.substring(1);  // drop '='
        String[] parts = f.split("\\+");
        int sum = 0;
        for(String p:parts)
        {
            if(p.length()==0)
            {
                continue;
            }
            char first=p.charAt(0);
             if (first >= 'A' && first <= 'Z') {
                // cell reference
                int col = first - 'A';
                int row = Integer.parseInt(p.substring(1));
                sum += sheet[row][col];
            } else {
                // numeric literal
                sum += Integer.parseInt(p);
            }
        }
        return sum;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
