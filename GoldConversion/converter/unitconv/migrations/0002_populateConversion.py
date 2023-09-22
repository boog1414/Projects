from django.db import migrations

def populate_db(apps, schema_editor):
    Conversions = apps.get_model('unitconv', 'Conversion')

    a = Conversions(units="T", conversion_number=0.0005)
    a.save()
    b = Conversions(units="g", conversion_number=453.592)
    b.save()
    c = Conversions(units="t_oz", conversion_number=14.5833)
    c.save()
    d = Conversions(units="oz", conversion_number=16)
    d.save()
    e = Conversions(units="lb", conversion_number=1)
    e.save()
    f = Conversions(units="kg", conversion_number=0.453592)
    f.save()




class Migration(migrations.Migration):
    dependencies = [('unitconv', '0001_initial')]
    operations = [migrations.RunPython(populate_db)]
