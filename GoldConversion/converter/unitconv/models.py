from django.db import models
class Conversion(models.Model):
    units = models.CharField(max_length=10)
    conversion_number = models.FloatField()
