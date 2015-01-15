package ru.relex.pt.dto;

abstract public class IdableDTO implements Idable
{
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(this.getClass().isAssignableFrom(obj.getClass())))
            return false;
        Idable other = (Idable) obj;
        if (getId() == null)
        {
            if (other.getId() != null)
                return false;
        }
        else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
