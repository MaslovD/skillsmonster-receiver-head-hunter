TRUNCATE TABLE public.remove_vacancy CONTINUE IDENTITY CASCADE ;
TRUNCATE TABLE public.processing_queue CONTINUE IDENTITY CASCADE;
UPDATE public.search_result SET status='NEW';
TRUNCATE TABLE public.search_result CONTINUE IDENTITY CASCADE;
