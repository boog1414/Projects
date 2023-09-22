from django.http import JsonResponse

from .models import Conversion

def convert(request):
    try:
        from_weight_type = request.GET.get('from')
        to_weight_type = request.GET.get('to')
        weight = float(request.GET.get('value'))

        result = (weight/Conversion.objects.get(units=from_weight_type).conversion_number)\
                 * Conversion.objects.get(units=to_weight_type).conversion_number


        weight_dict = {
        "units": to_weight_type,
        "value": result,
        }
        return JsonResponse(weight_dict)
    except:
        err_dict = {
            "error": "invalid unit conversion request"
        }
        return JsonResponse(err_dict)

