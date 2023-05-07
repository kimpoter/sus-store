package susstore.susstore.plugin;

public interface Plugin
{
    public void run();
}

// di program utama
interface Currency
{
    public double getValue();
}

class IDR implements Currency
{
    private double value;

    public double getValue()
    {
        return this.value;
    }
}


// di plugin
class USD implements Currency
{
    private double value;
    public double getValue()
    {
        return this.value * 0.05;
    }
}

class Bill
{
    Currency currency;

    public Bill(Currency c)
    {
        this.currency = c;
    }

    public void setCurrency(Currency c)
    {
        this.currency = c;
    }
}

