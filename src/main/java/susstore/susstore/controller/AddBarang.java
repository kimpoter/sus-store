package susstore.susstore.controller;

import susstore.susstore.datastore.Storable;
import java.util.ArrayList;

public class AddBarang implements Command
{
    private ArrayList<Storable> list;

    private Storable item;

    public AddBarang(ArrayList<Storable> list, Storable item)
    {
        this.list = list;
        this.item = item;
    }

    @Override
    public void execute()
    {
        this.list.add(this.item);
    }
}